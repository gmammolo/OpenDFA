# OpenDFA

Il progetto contiene un'interfaccia grafica che permette di creare un' automa a stati finiti (DFA) e la libreria che permette di usarlo anche senza interfaccia grafica.
Essendo in una fase iniziale, molte funzionalità ancora assenti e la configurazione
può risultare macchinosa.

## Configurazione

La configurazione è necessaria per un corretto funzionamento della GUI e in generale
per permettere la creazione di png con il grafo dell' automa.
Per prima cosa è necessario installare Graphviz sul sistema.
in 
```sh
./config/config.properties
```  

sono contenute le seguenti variabili:
```sh
dotForLinux=dot
dotForWindows=dot.exe
tempDir=/home/terasud/NetBeansProjects/OpenDFA/Temp/
outputDir=/home/terasud/NetBeansProjects/OpenDFA/Output/

```
dotForLinux e dotForWindows contengono i comandi per richiamare graphviz.
Linux crea automaticamente il comando dot, mentre windows richiede che venga 
aggiunto dot.exe tra le variabili di sistema.
le cartelle temp ed output invece servono per un corretto funzionamento.
Vanno quindi  create prima di avviare il progetto.
 