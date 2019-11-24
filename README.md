# Satdoku

SAT-based Sudoku puzzle solver.

We want to encode a Sudoku puzzle as a boolean satisfiability problem so we can feed it to a SAT solver.

There are a base set of constraints:
1. for each cell in each row, no other cells in the same row share that one's value
2. for each cell in each column, no other cells in the same column share that one's value
3. for each cell in each minisquare, no other cells in the same minisquare share that one's value

I'm not sure how to encode those requirements.

For a given cell C(r,c), we need to represent integer assignment as a boolean expression. So I guess it would be
`A(r,c,n)`, which means an assignment of the number `n` to the cell `C(r,c)`.

So for any two cells, let's say `C(0,0)` and `C(0,1)`, we have `(A(0, 0, 1) | A(0,1,1)) && (!A(0, 0, 1) | !A(0, 1, 1))`.
i.e., the value `1` is assigned to one of the cells, but at least one of them does *not* have it assigned.

So for the two possible values for those cells, we'd have
```
A(0,0,1) | A(0, 1, 1)       # One of them is assigned the value 1
!A(0, 0, 1) | !A(0, 1, 1)   # One of them is not assigned the value 1
A(0, 0, 2) | A(0, 1, 2)     # One of them is assigned the value 2
!A(0, 0, 2) | !A(0, 1, 2)   # One of them is not assigned the value 2
```

How does that generalize? Let's look at three cells and three possible values `1,2,3`. Since we're working in 1
dimension right now, let's abbreviate `C(0, X)` as `C(X)`, and `A(0, X, N)` as `A(X, N)`.

We basically want to consider all three cells, and ensure that at least one of them is assigned the value 1. Then
consider each pair of numbers, and ensure that at least one of each possible pair is NOT assigned the value 1.

```
A(0, 1) | A(1, 1) | A(2, 1)  # one of them is assigned the value 1
# no two cells share the value 1
!A(0,1) | !A(1,1)
!A(0,1) | !A(2,1)
!A(1,1) | !A(2,1)
```
And similarly for the remaining numbers.

So our basic Sudoku constraint is just that expansion for each row, each column, and each minisquare.

And then we'd add additional boolean assignments for the filled-in numbers at the end.

## Encoding

Our SAT engine doesn't understand `A(y,x,n)` terminology. It only understands ordinal variable names -- i.e., variables
named `1`, `2`, etc. with no gaps.

So we need to be able to encode statements about `A(y,x,n)` into numbers.

That's easy enough, right? We have 9 possible values of `x`, 9 possible values of `y`, and 9 possible values of `n`.
So the encoding of `A(y,x,n)` into an orginal variable is just `((y * 9) + x) * 9 + n`. Or, `y*81 + x*9 + n`.

And when we decode the output, we can just reverse that encoding.
