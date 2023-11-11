# Los Financeros Hermanos

## Histórias de usuários

-Como usuário, eu quero poder cadastrar minha conta e fazer login no sistema.
-Como usuário, eu quero consultar, adicionar, editar e deletar meus ganhos.
-Como usuário, eu quero consultar, adicionar, editar e deletar meus gastos.
-Como usuário, eu quero ter a opção de trocar minha senha caso tenha a esquecido.
-Como usuário, eu querto ter a opção de fazer logout do sistema.

[![Open in online IDE ](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/vaadin/flow-quickstart-tutorial)


## Diagrama de classe UML
```Java
____________________________________________________________________________       _________________________________________________________________________________________________________
|                             GainController                                |      |                                           SpentController                                             |      
|___________________________________________________________________________|      |_______________________________________________________________________________________________________|      
| - id_gain: Integer                                                        |      | - id_gain: Integer                                                                                    |      
| - tipo: String                                                            |      | - tipo: String                                                                                        |      
| - data: Date                                                              |      | - data: Date                                                                                          |
| - valor: Double                                                           |      | - valor: Double                                                                                       |      
|                                                                           |      | - formaPagamento: String                                                                              |
| +GainController(id_gain: Integer, tipo: String, date: Date, valor: double)|      |                                                                                                       |
| +getId(): Integer                                                         |      | +SpentController(id_spent: Integer, tipo: String, date: Date, valor: double, formaPagamento: String)  |
| +getTipo(): String                                                        |      | +getId(): Integer                                                                                     |
| +setTipo(tipo: String): void                                              |      | +getTipo(): String                                                                                    |
| +getData(): Date                                                          |      | +setTipo(tipo: String): void                                                                          |
| +setData(data: Date): void                                                |      | +getData(): Date                                                                                      |
| +getValor(): double                                                       |      | +setData(data: Date): void                                                                            |
| +setValor(valor: double): void                                            |      | +getValor(): double                                                                                   |
|                                                                           |      | +setValor(valor: double): void                                                                        |
|                                                                           |      | +getFormaPagamento(): String                                                                          |
|                                                                           |      | +setFormaPagamento(formaPagamento: String): void                                                      |
|___________________________________________________________________________|      |_______________________________________________________________________________________________________|
___________________________________________________________________________
|                             UserController                               |
|___________________________________________________________________________|
| - userId: Integer                                                         |
| - username: String                                                        |
| - password: String                                                        |
|                                                                           |
| + UserController(username: String, password: String)                      |
| + getId(): Integer                                                        |
| + setUserId(id: Integer): void                                            |
| + getUsername(): String                                                   |
| + setUsername(username: String): void                                      |
| + getPassword(): String                                                   |
| + setPassword(password: String): void                                      |
|___________________________________________________________________________|

```
