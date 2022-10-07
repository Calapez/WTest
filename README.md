# WTest

A recolha dos códigos postais é feita quando a app inicializa, sendo que todos os códigos postais são guardados numa base de dados SQLite local, servindo assim como cache em disco. Para este efeito foi utilizada a biblioteca da Google, Room. Quando a app é reinicializada, se já existirem códigos postais na base de dados local, estes não são transferidos novamente, ao invés, são utilizados os dados que estão em cache.
Quando os códigos postais são transferidos, é apresentado um texto a indicá-lo.

Quando os códigos postais estiverem transferidos, é possível navegar para o ecrã de pesquisa de códigos postais. A filtração dos códigos postais com base na pesquisa é feita de forma assíncrona através de queries SQLite aos códigos postais que estão em cache, o que apresenta uma boa performance, uma vez que não coloca demasiada carga na memória do dispositivo.
Foi implementada paginação neste ecrã de forma a diminuir o número itens que são manipulados na lista apresentada inicialmente, quando não existe pesquisa.

Semelhante aos códigos postais, os artigos são transferidos e guardados em cache. A diferença é que os artigos são transferidos sempre que se navega para o ecrã que visualizar artigos, de forma a manter os dados o mais atualizados possível. Se, no entanto, não existir internet, os artigos em cache serão apresentados ao utilizador.
O ecrã de lista de artigos também possui paginação, para prevenir que todos os dados sejam transferidos de uma vez.
Em relação ao ecrã de detalhes do artigo, foi utilizada a biblioteca Glide para apresentar o avatar como imagem através do URL.
