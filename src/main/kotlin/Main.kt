import kap.*
import kotlinx.coroutines.delay

// ── Your domain types ──────────────────────────────────────────

@KapTypeSafe
data class Dashboard(val user: String, val cart: String, val promos: String)

// ── Your services (replace with real API calls) ────────────────

suspend fun fetchUser(): String { delay(50); return "Alice" }
suspend fun fetchCart(): String { delay(40); return "3 items, $147.50" }
suspend fun fetchPromos(): String { delay(30); return "SAVE20" }

// ── Main ───────────────────────────────────────────────────────

suspend fun main() {
    // All three services run in parallel.
    // Total time ≈ max(50, 40, 30) = 50ms, not 120ms.
    //
    // @KapTypeSafe generates .withUser, .withCart, .withPromos from
    // the constructor parameters. The IDE autocompletes the next step.
    // Swap two? Doesn't compile.
    val dashboard: Dashboard = kap(::Dashboard)
        .withUser { fetchUser() }       // ┐
        .withCart { fetchCart() }        // ├─ parallel
        .withPromos { fetchPromos() }   // ┘
        .evalGraph()

    println(dashboard)
    // Dashboard(user=Alice, cart=3 items, $147.50, promos=SAVE20)

    // Next steps:
    // - Change .withX to .thenX for phase barriers (sequential dependencies)
    // - Use .andThen { result -> } for value-dependent phases
    // - Add kap-resilience for retry, circuit breaker, timeout
    // - See https://damian-rafael-lattenero.github.io/kap/ for full docs
}
