# KAP Starter

Starter template for [KAP — Kotlin Async Parallelism](https://github.com/damian-rafael-lattenero/kap).

## Quick start

```bash
# Clone this template
git clone https://github.com/damian-rafael-lattenero/kap-starter.git my-project
cd my-project

# Run
./gradlew run
```

## What's included

- `kap-core` dependency configured
- A minimal `Main.kt` with a parallel execution example
- Gradle Kotlin DSL with JDK 21
- Optional modules commented out in `build.gradle.kts` — uncomment what you need

## Modules

| Module | What you get |
|---|---|
| `kap-core` | `with`, `then`, `andThen`, `race`, `traverse`, `memoize`, `timeout`, `recover` |
| `kap-resilience` | `Schedule`, `CircuitBreaker`, `Resource`, `bracket`, `timeoutRace`, `raceQuorum` |
| `kap-arrow` | `zipV`, `withV`, `validated {}`, `attempt()`, `raceEither` |
| `kap-ktor` | Ktor plugin, circuit breaker registry, `respondAsync` |
| `kap-kotest` | Test matchers: `shouldSucceedWith`, `shouldFailWith`, timing assertions |

## Links

- [Documentation](https://damian-rafael-lattenero.github.io/kap/)
- [GitHub](https://github.com/damian-rafael-lattenero/kap)
- [Quickstart Guide](https://damian-rafael-lattenero.github.io/kap/guide/quickstart/)
