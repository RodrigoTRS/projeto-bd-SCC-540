/* SELECIONA OS MORADORES APTOS PARA ADOCAO */

SELECT M.NOME, M.TELEFONE, M.CEP
FROM MORADOR M
WHERE
(M.RENDA_FAMILIAR/(M.NUM_FILHOS+2) > 1120) /* Renda maior que 1 salário mínimo por pessoa */
AND M.NUM_FILHOS <= 2 /* Não mais do que dois filhos */
AND M.NUM_PETS <= 1 /* Não mais do que um pet */
AND M.IDADE <= 65; /* Idade do responsável menor que 65 */

/* SELECIONE OS ANIMAIS APTOS PARA ADOCAO */

SELECT AN.ID_COLEIRA, AN.NOME, AN.CANIL, AN.TIPO, AN.IDADE
FROM ANIMAL AN LEFT JOIN ADOCAO AD
ON AN.ID_COLEIRA=AD.ANIMAL
WHERE AN.ISCASTRADO = 'S' /* Animal castrado */
AND AN.ISVERMIFUGADO = 'S' /* Animal vermifugado */
AND AN.ID_COLEIRA NOT IN (SELECT ANIMAL FROM ADOCAO) /* Animal ainda não adotado */

/* SELECIONA AS ENTIDADES QUE MAIS DOARAM NA HISTÓRIA */

SELECT E.NOME, SUM(C.VALOR) AS VALOR_DOADO
FROM ENTIDADE_PARCEIRA E JOIN CONTRIBUICAO C
ON E.NOME = C.ENTIDADE
GROUP BY E.NOME;