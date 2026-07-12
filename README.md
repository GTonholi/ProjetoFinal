# Sistema de Gerenciamento de Cafeteria
### Projeto Final — Programação Orientada a Objetos

Sistema de console desenvolvido em Java para gerenciamento de pedidos e autenticação de funcionários de uma cafeteria. O projeto foi construído com foco em boas práticas de Programação Orientada a Objetos, aplicando design patterns consolidados e princípios de baixo acoplamento entre camadas.

## Visão Geral

O sistema simula o funcionamento de uma cafeteria, permitindo que clientes visualizem o cardápio, montem pedidos personalizados e efetuem pagamentos, enquanto funcionários autenticados podem acompanhar e atualizar o status de preparo dos pedidos em andamento.

O projeto foi organizado em camadas coesas (`model`, `service`, `view`, `repository`, `exception`), com forte ênfase em encapsulamento, polimorfismo e reutilização de código através de tipos genéricos.

## Funcionalidades

- **Autenticação de funcionários**, com controle de tentativas e sessão de login/logout.
- **Visualização do cardápio informativo**, exibindo preços base, sabores/opções e adicionais disponíveis para cada item.
- **Montagem de pedidos personalizados**, com adição de comidas e bebidas customizáveis (sabores, coberturas, extras) através dos padrões Builder e Decorator.
- **Múltiplos métodos de pagamento** (dinheiro, Pix, cartão de crédito, cartão de débito) por meio do padrão Strategy, com possibilidade de cancelamento do pedido antes da finalização.
- **Painel de pedidos**, exibindo todos os pedidos registrados e permitindo consulta por status.
- **Atualização do status de preparo** dos pedidos pelo funcionário autenticado, seguindo uma progressão definida (pendente, preparando, pronto, entregue).
- **Tratamento de exceções customizado**, distinguindo erros de sistema (unchecked) de violações de regras de negócio (checked).

## Tecnologias e Requisitos

- **Java 17** (ou superior)
- **Maven** para gerenciamento de dependências e build do projeto

## Estrutura do Projeto

Cada pacote tem uma responsabilidade única e bem definida:

| Pacote | Responsabilidade |
|---|---|
| `model` | Entidades e regras de negócio do domínio (Pedido, Cliente, Funcionario, Carrinho, Produto, etc.) |
| `repository` | Abstração de acesso e armazenamento de dados (atualmente em memória) |
| `service` | Orquestração de fluxos de negócio (login, criação de pedidos, geração do cardápio informativo) |
| `view` | Interação com o usuário via console (menus, painel de pedidos) |
| `exception` | Exceções customizadas para erros de sistema e violações de regras de negócio |

## Arquitetura e Padrões de Projeto

O sistema aplica os seguintes padrões de projeto, escolhidos para resolver problemas específicos do domínio:

### State (Estado)

A interface `StatusPedido`, com as implementações `StatusPendente`, `StatusPago` e `StatusCancelado`, controla o ciclo de vida de pagamento de um pedido. Cada estado define seu próprio comportamento para as operações `pagar` e `cancelar`, evitando estruturas condicionais complexas (`if`/`switch` sobre um enum de status) espalhadas pelo código.

### Strategy (Estratégia)

A interface `MetodoPagamento`, com as implementações `Dinheiro`, `Pix`, `CartaoCredito` e `CartaoDebito`, permite que o cliente escolha como o pagamento será processado sem que `Pedido` ou `StatusPedido` precisem conhecer os detalhes de cada forma de pagamento. Novos métodos de pagamento podem ser adicionados sem alterar nenhuma classe existente.

### Decorator (Decorador)

Os pacotes `comida.decorators` e o equivalente para bebidas permitem compor adicionais (coberturas, extras) sobre um item base sem a necessidade de criar uma subclasse para cada combinação possível de personalizações.

### Builder (Construtor)

As interfaces `ComidaBuilder` e `BebidaBuilder` guiam a interação com o cliente para personalizar um item (sabores, tamanhos, adicionais) antes de construí-lo, separando a lógica de coleta de informações da lógica de construção do objeto final.

### Repository (Repositório) e Inversão de Dependência

Os pacotes de repositório expõem interfaces (`IFuncionarioRepository`, `IPedidoRepository`) implementadas por classes concretas (`FuncionarioRepository`, `InMemoryPedidoRepository`). Isso segue o Princípio da Inversão de Dependência (DIP): as camadas de serviço dependem de abstrações, não de implementações concretas, permitindo trocar o mecanismo de armazenamento (por exemplo, para um banco de dados) sem alterar nenhuma classe consumidora.

### Polimorfismo Paramétrico (Generics)

A classe `ItemContainer<T extends Produto>` elimina a duplicação de código entre o controle de comidas e bebidas no carrinho, ao mesmo tempo em que preserva a identidade semântica estrita entre as categorias: um `ItemContainer<Comida>` e um `ItemContainer<Bebida>` são tipos incompatíveis em tempo de compilação, eliminando qualquer risco de `ClassCastException` por mistura acidental de categorias.

### Encapsulamento e Delegação de Responsabilidade

Em diversos pontos do sistema, a decisão de "como validar algo" é delegada ao próprio objeto responsável pela informação, em vez de ser feita externamente.

## Como Executar

### Compilar o projeto

```bash
mvn clean compile
```

### Executar a aplicação

```bash
mvn exec:java
```

### Gerar o executável (.jar)

```bash
mvn package
java -jar target/projeto-cafeteria.jar
```

## Fluxo de Uso

Ao iniciar o sistema, o usuário escolhe entre três opções no menu principal:

1. **Visualizar o painel de pedidos** — lista todos os pedidos já registrados, com cliente, status e valor total.
2. **Acessar como cliente** — permite consultar o cardápio informativo, montar um novo pedido (adicionando comidas e bebidas personalizadas) e finalizar com o pagamento escolhido (ou cancelar antes de efetuar o pagamento).
3. **Acessar como funcionário** — exige autenticação (login e senha) e permite atualizar o status de preparo dos pedidos em andamento.

Credenciais de funcionário disponíveis para teste (definidos no repositório em memória):

| Login | Senha |
|---|---|
| ana | 1234 |
| carlos | abcd |