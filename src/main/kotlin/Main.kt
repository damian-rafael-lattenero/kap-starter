import kap.*
import kotlinx.coroutines.delay

// ── Your domain types ──────────────────────────────────────────

data class Dashboard(val user: String, val cart: String, val promos: String)

// ── Your services (replace with real API calls) ────────────────

suspend fun fetchUser(): String { delay(50); return "Alice" }
suspend fun fetchCart(): String { delay(40); return "3 items, $147.50" }
suspend fun fetchPromos(): String { delay(30); return "SAVE20" }

// ── Main ───────────────────────────────────────────────────────

suspend fun main() {
    // All three services run in parallel.
    // Total time = max(50, 40, 30) = 50ms, not 120ms.
    // Swap any two .with lines → compile error.
    val dashboard: Dashboard = Async {
        kap(::Dashboard)
            .with { fetchUser() }     // ┐
            .with { fetchCart() }      // ├─ parallel
            .with { fetchPromos() }    // ┘
    }

    println(dashboard)
    // Dashboard(user=Alice, cart=3 items, $147.50, promos=SAVE20)

    // Next steps:
    // - Add .then { } for phase barriers
    // - Add .andThen { ctx -> } for value-dependent phases
    // - See https://damian-rafael-lattenero.github.io/kap/ for full docs
}
