package patterns

object traits {
  trait Logger {
    def log(msg: String): Unit
    def logInfo(msg: String) = log("[Info] " + msg)
    def logError(msg: String) = log("[Error] " + msg)
  }

  trait ConsoleLogger extends Logger {
    def log(msg: String) { println(msg) }
  }

  trait FileLogger extends Logger

  class Account {
    self: Logger =>
    var balance = 0
    def withdraw(amount: Double) {
      if (amount > balance) self.logError("Insufficient funds")
      else self.logInfo("...")
    }
  }

  class AccountCL extends Account with ConsoleLogger

  val acc = new AccountCL
  trait ShowAccount {
    self: Account =>
    def show = "Balance: " + self.balance
  }

  val acc1 = new Account with ConsoleLogger with ShowAccount
  acc1.show // ok

  val acc2 = new Account with ConsoleLogger
  //acc2.show // error
}
