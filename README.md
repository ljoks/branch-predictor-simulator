# branch-predictor-simulator
Simulates smith n-bit predictor, bimodal predictor, and Gshare predictor

## To run
In `src/`, run `make` to build java `.class` files.

Then run one of
```
java sim smith <bits> <trace file path>
```
```
java sim bimodal <bits> <trace file path>
```
```
java sim gshare <m> <n> <trace file path>
```
where `m` is the number of low-order PC bits used to form the prediction table index,
and `n` is the number of bits in the global branch history register.
