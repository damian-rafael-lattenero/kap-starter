# KAP Starter

Starter template for [KAP — Kotlin Async Parallelism](https://github.com/damian-rafael-lattenero/kap).

## Quick start

```bash
git clone https://github.com/damian-rafael-lattenero/kap-starter.git my-project
cd my-project
./gradlew run
```

## What's included

- `kap-core` + `kap-ksp` configured with `@KapTypeSafe` named builders
- A minimal `Main.kt` with a parallel execution example
- KSP generates the scoped wrapper (`DashboardKap`) from your constructor:
  inside `.with { field from value }` the lambda's implicit receiver narrows
  the IDE autocomplete to the field you owe — swapping two fields is a
  compile error naming the expected tag
- Gradle Kotlin DSL: JDK 25 toolchain, bytecode target 21 — the same single-JVM law as kap itself
- Optional modules commented out in `build.gradle.kts`, including
  `kap-detekt-rules` — the kap doctrine (no busy-waits, no swallowed
  cancellation, no `@Volatile` RMW cells) as a mechanical detekt gate

## Modules

| Module | What you get |
|---|---|
| `kap-core` | `.with`/`.then`/`.andThen`, `race`, `traverse`, `memoize`, `settled`, `timed` |
| `kap-ksp` | `@KapTypeSafe` named builders — swap two fields and the compiler names the expected tag |
| `kap-resilience` | `Schedule`, `CircuitBreaker`, `Resource`, `bracket`, `timeoutRace`, `raceQuorum`, `Supervisor`, `Process` |
| `kap-stream` | `KapStream` — backpressured pipelines whose stages are `Kap`s, with named per-stage policies |
| `kap-arrow` | `kapV`, `.withV` — parallel validation with error accumulation (`Either<Nel<E>, A>`) |
| `kap-ktor` | Ktor plugin, circuit breaker registry, `respondAsync` |
| `kap-kotest` | Test matchers: `shouldSucceedWith`, `shouldFailWith`, timing assertions |
| `kap-detekt-rules` | The doctrine as a detekt gate: `ManualWaitLoop`, `CancellationArmBeforeCatchAll`, `VolatileReadModifyWrite` |

## Links

- [Documentation](https://damian-rafael-lattenero.github.io/kap/)
- [GitHub](https://github.com/damian-rafael-lattenero/kap)
- [Quickstart Guide](https://damian-rafael-lattenero.github.io/kap/guide/quickstart/)
