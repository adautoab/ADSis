# ADSis

Sistema para gerenciamento de estimativa de alncace de anúncios na internet

Sistema feito em Java com JPA e Hibernate. Para este projeto foi utilizado o Postgres.
Para executar precisa ter o Postgres instalado e criar o Banco de Dados vazio chamdo adsis_banco (o Hibernate se encarregará de criar as tabelas)
ou configurar o arquivo persistence.xml. No arquivo persistence.xml essa linha é responsável pela configuração
do acesso ao banco.

<property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/adsis_banco"/>

Projeto criado usando o Netbeans, pode-se baixar o projeto executar ele pela Ide.

O projeto consiste de uma tela inical onde pode-se escolher entre as opções: Clientes, Anúncios ou Relatórios.

Na opção Clientes abrirá tela de Cadastro de Clientes, onde pode-se fazer a inserção ou atualização de clientes;
Na opção Anúncios abrirá tela de Cadastro de Anúncios, onde pode-se fazer a inserção ou atualização de anúncios, estes relacionados com seus respectivos clientes;
Na opção Relatórios é possível selecionar um anúncio cadastrado que exibirá as estimativas de Cliques, Compartilhamentos e Visualizações dos mesmos com base no valor
valor investido diário levando-se em consideração o número de dias de duração do Anúncio.
