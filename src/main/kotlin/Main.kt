import kap.*
import DashboardKap.*
import kotlinx.coroutines.delay

// ── Your domain types ──────────────────────────────────────────

// @KapTypeSafe generates DashboardKap: a scoped wrapper whose .with { field from value }
// lambdas expose exactly the slot you owe, by name. Swap two fields? Compile error,
// naming the expected tag.
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
    val dashboard: Dashboard = kap(::Dashboard)
        .with { user from fetchUser() }     // ┐
        .with { cart from fetchCart() }     // ├─ parallel
        .with { promos from fetchPromos() } // ┘
        .evalGraph()

    println(dashboard)
    // Dashboard(user=Alice, cart=3 items, $147.50, promos=SAVE20)

    // Next steps:
    // - Change .with to .then for phase barriers — "once that's done..."
    // - Use .andThen { result -> } to decide the next graph from the result
    // - Wrap one call in Kap { fetchPromos() }.timeout(3.seconds) — resilience
    //   stays per-call and inline
    // - Add kap-resilience for retry(Schedule), circuit breakers, bracket
    // - See https://damian-rafael-lattenero.github.io/kap/ for full docs
}
