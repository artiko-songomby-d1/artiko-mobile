# Guidelines

## Docs
- Put every maquette and design document in `doc/`, nowhere else.
- Update `doc/maquette.svg` in the same commit as any screen change it describes.

## Commits
- Format: `type(scope): subject`, Conventional Commits.
- Types: `feat` `fix` `refactor` `docs` `test` `chore` `build` `ci` `style` `perf`.
- Scope: the single screen or module touched (`discovery`, `artist`, `exposition`, `art-view`, `create`, `learn`, `profile`, `settings`, `nav`, `core`) or `build`, `ci`, `maquette`, `guidelines`.
- One scope per commit. If the diff spans two scopes, split it (`git add -p`) into one commit per scope.
- Subject: imperative, lowercase, no trailing period, 72 characters max.
- Subject must not contain "and", "&", "+" or ",". If you need one, the commit is too big: split it.

## Tests
- Every feature has a test class in `src/test/.../feature/<feature>/`, mirroring the package of the code under test.
- Name the test class after the feature it verifies, in plain words, plus `Test` (`HallOfFameFirstInFeedTest`, `ArtistFollowTest`, `DraftRetryWhenOnlineTest`).
- Never name it after an implementation class (`DiscoveryViewModelTest`): a reader must learn the feature from the name alone.
- Add the test class in the same commit as the feature, with the same scope.

## Code
- Follow the official Kotlin code style.
- Names are full words that state intent. No abbreviations.
- A function does one thing and fits on one screen (about 20 lines). Extract otherwise.
- No magic numbers or strings: use a named constant.
- Comments explain why, never what. Delete commented-out code and dead code.
- Composables are stateless: hoist state, pass data and callbacks as parameters.
- No business logic in composables: it lives in the ViewModel, which exposes an immutable `StateFlow` of UI state.
- One public composable per file, with a `@Preview`.
