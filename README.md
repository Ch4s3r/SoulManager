# SoulManager
One manager to rule them all.

Requirements:<br>
https://gist.github.com/christophpickl/1f1847137d52d516c04d468d9aafad1c/revisions

# TODOs would be:

* good/bad Testcases
* Error messages for the user -> 500 internal server error
* concurrent testing/thread safety
* use interface for all services?!
* query.graphqls:
    * split up per use case such as 'user', 'transaction',... into own files
* use jwt refresh token (optional)
* transactions:
    * create for each send/deposit/withdraw
    * list own transactions
    * admin can list all transactions
* satisfy customer ;)
