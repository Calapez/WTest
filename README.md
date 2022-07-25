# WTest

Funcionalidade 1

A recolha dos códigos postais é feita quando a app inicializa, sendo que todos os códigos postais são guardados numa base de dados SQLite local, servindo assim como cache em disco. Para este efeito foi utilizada a biblioteca da Google, Room. Quando a app é reinicializada, se já existirem códigos postais na base de dados local, estes não são transferidos novamente, ao invés, são utilizados os dados que estão em cache.
Quando os códigos postais são transferidos, é apresentado um texto a indicá-lo.

Funcionalidade 2

Quando os códigos postais estiverem transferidos, é possível navegar para o ecrã de pesquisa de códigos postais. A filtração dos códigos postais com base na pesquisa é feita de forma assíncrona através de queries SQLite aos códigos postais que estão em cache, o que apresenta uma boa performance, uma vez que não coloca demasiada carga na memória do dispositivo.
Nota: Foi implementada paginação neste ecrã de forma a diminuir o número itens que são manipulados na lista apresentada inicialmente, quando não existe pesquisa.

Funcionalidade 3

Semelhante aos códigos postais, os artigos são transferidos e guardados em cache. A diferença é que os artigos são transferidos sempre que se navega para o ecrã que visualizar artigos, de forma a manter os dados o mais atualizados possível. Se, no entanto, não existir internet, os artigos em cache serão apresentados ao utilizador.
O ecrã de lista de artigos também possui paginação.
Em relação ao ecrã de detalhes do artigo, foi utilizada a biblioteca Glide para apresentar o avatar como imagem através do URL.

Funcionalidade 4

O ecrã de formulário possui todos os campos pedidos. Tal como sugerido, o campo de Código Postal reutiliza o ecrã da Funcionalidade 2. 
Existe um botão para submeter o formulário, o que irá fazer com que os campos sejam validades, e que sejam apresentados erros se estes não estiverem corretos. 
No entanto, devido a falta de tempo, faltou finalizar a verificação dos campo de data. Este campo tem como única validação o seu conteúdo não estar vazio.

Funcionalidade 5

Não foi implementada devido a falta de tempo.