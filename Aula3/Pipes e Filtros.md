*Pipes e Filtros

-Conceito e definição: estilo de arquitetura onde um processamento é divido em varias etapas independentes chamadas de filtros

-Casos de uso comuns

    --Sistemas de ia: Ele tokeniza e quebra a interpretação dos dados em varias etapas

    --Processamento de documentos: extração de texto → limpeza → classificação → armazenamento.

-Principais vantagens:
Modularidade: cada filtro possui uma responsabilidade específica.
Reutilização: filtros podem ser utilizados em diferentes pipelines.
Facilidade de manutenção: alterações em uma etapa tendem a afetar menos as demais.
Flexibilidade: é possível adicionar, remover ou substituir filtros.
Paralelismo: determinadas etapas podem ser executadas simultaneamente, dependendo do fluxo.
Separação de responsabilidades: o processamento é dividido em componentes menores e mais fáceis de compreender.

-Principais desvantagens:
Overhead de comunicação: a passagem de dados entre filtros pode gerar custos de processamento.
Complexidade de gerenciamento: pipelines grandes podem se tornar difíceis de monitorar e depurar.
Dependência entre etapas: um problema em um filtro pode interromper ou comprometer todo o pipeline.
Latência: muitas etapas sequenciais podem aumentar o tempo total de processamento.
Transformação de dados: filtros podem precisar converter os dados para formatos compatíveis entre si.
Nem todo problema se encaixa no padrão: sistemas com muitos fluxos condicionais, loops ou forte interação entre componentes podem ser mais adequados a outros padrões arquiteturais.
