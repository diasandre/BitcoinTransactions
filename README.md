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

<h2>GET</h2>

<h3>Maiores transações</h3>
<ul>
  <li>type: buy ou sell</li>
  <li>limit: número de itens</li>
</ul>

<pre>
/{type}/largest/{limit}
</pre>
