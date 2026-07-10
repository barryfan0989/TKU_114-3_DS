問題：ATM 提款

input:
- balance
- withdrawAmount

process:
- 判斷 withdrawAmount > 0
- 判斷 withdrawAmount <= balance
- balance = balance - withdrawAmount

output:
- withdrawAmount
- balance

