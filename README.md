<img src="https://upload.wikimedia.org/wikipedia/commons/c/cf/Bitcoin.com_logo.png"/>

<h1>andréBITCOIN</h1>

<a href="https://codebeat.co/projects/github-com-diasduzurf-andrebitcoin-master"><img alt="codebeat badge" src="https://codebeat.co/badges/1311f527-e216-47ef-839f-f9e7086e4941" /></a>

<p>Usa a API do https://www.mercadobitcoin.com.br/ para visualizar dados de venda e compra de BITCOIN em períodos selecionados:<p>
<ul>
<li>Variação do preço</li>
<li>Maiores transações</li>
<li>Média de gastos</li>
<li>Mediana</li>
<li>Desvio de padrão corrigido</li>
</ul>

<p>
Para rodar:
<pre> npm start </pre>
</p>

<p>
Storybook:
<pre> npm run storybook </pre>
</p>

<p> Endereço base do backend: http://localhost:8080/endpoint/ </p>
<p> Necessário uso do Tomcat</p>

<h2>GET</h2>

<h3> Status </h3>
<pre>
/data/status
</pre>

<h3> Dados </h3>
<ul>
  <li>Cinco maiores transações de venda e compra</li>
  <li>Média</li>
  <li>Mediana</li>
  <li>Desvio padrão corrigido</li>
</ul>

<ul>
  <li>fromDate: data inicial de pesquisa dos dados em timestamp - Long - deve ser usado em conjunto com toDate</li>
  <li>toDate: data final de pesquisa dos dados em timestamp - Long - deve ser usado em conjunto com fromDate</li>
</ul>

<pre>
/data/{fromDate}/{toDate}
</pre>

<h3>Maiores transações</h3>
<ul>
  <li>type: buy ou sell - String (Obrigatório)</li>
  <li>limit: número de itens - Integer - valor default é 5</li>
  <li>fromDate: data inicial de pesquisa dos dados em timestamp - Long - deve ser usado em conjunto com toDate</li>
  <li>toDate: data final de pesquisa dos dados em timestamp - Long - deve ser usado em conjunto com fromDate</li>
</ul>

<pre>
/data/{fromDate}/{toDate}/{type}/largest/{limit}
</pre>

<h3>Média de gastos</h3>
<ul>
<li>type: buy ou sell - String (Obrigatório)</li>
</ul>

<pre>
/data/{type}/average
</pre>

<h3>Mediana dos gastos</h3>
<ul>
<li>type: buy ou sell - String (Obrigatório)</li>
</ul>

<pre>
/data/{type}/median
</pre>

<h3>Desvio padrão corrigido dos gastos</h3>
<ul>
  <li>type: buy ou sell - String (Obrigatório)</li>
</ul>

<pre>
/data/{type}/deviation
</pre>

<h3>ChartJS configurações</h3>
<ul>
  <li>type: buy ou sell - String (Obrigatório)</li>
  <li>fromDate: data inicial de pesquisa dos dados em timestamp - Long - deve ser usado em conjunto com toDate</li>
  <li>toDate: data final de pesquisa dos dados em timestamp - Long - deve ser usado em conjunto com fromDate</li>
</ul>

<pre>
/chart/{fromDate}/{toDate}/{type}
</pre>

<h2>Problemas conhecidos </h2>

<p> A API somente retorna mil dados por consulta e existem mais de mil transações por dia portanto somente serão retornados 1000 transações apartir do fromDate</p>
