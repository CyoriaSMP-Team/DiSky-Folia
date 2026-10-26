# DiSky-Folia — CyoriaSMP Folia release provenance

- Original/source project: https://github.com/DiSkyOrg/DiSky
- Original license: Apache-2.0. Upstream authorship and licensing are preserved.
- Tested target: Minecraft/Folia 1.21.11.
- Stage: **Experimental patched binary**. Pre-release only, not certified region/thread safe.
- Binary: `DiSky-4.29.0-CyoriaFolia.jar` (GitHub Releases asset; no worlds/config/secrets).

## What is actually modified
Changed DiSky scheduler and Skript callback continuations; see cyoria-folia-patches, local binary changes include additional altered classes beyond archived Java helpers.

## Verification scope
Folia startup and 76 custom arguments registered; real Discord bot sessions and multi-region Skript effects untested.

## Limitations
This binary includes additional patched bytecode; a fully reproducible build process has NOT been established. Back up your data and test on a disposable world before deployment.

Source fork retains original project history. This document does not assert that a successful startup means fully Folia-compatible gameplay.
