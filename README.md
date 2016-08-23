# pojo-converter-api
API para converter POJOs para outros formatos, como exemplo para JSON.


# Utilização
1 - gerar um .jar e adicionar na aplicação.<br>
2 - Utilizar a fabrica ConverterFactory, método getConverter, passando como paramentro a String "JSON";<br>
3 - Criar os POJOS (Os mesmos devem implementar a interface Convertable)<br>
4 - Criar o OutPutStream<br>
5 - Efetuar a chamada do método converter, para converter 1 POJO ou o método converterAll para converter uma lista de POJOS<br>
6 - O retorno será um JSON formatado.<br>

# Importante
A API está preparada para converter POJO para JSON. Futuramente serão incluidas possiblidade de conversão para outros formatos, como XML e CSV.

