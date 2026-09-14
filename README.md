# 📝 To-Do List App

Aplicativo Android para gerenciar suas tarefas diárias com armazenamento local.

## ✨ Recursos

✅ Adicionar, editar e deletar tarefas
✅ Marcar tarefas como concluídas
✅ Armazenamento local com SQLite/SharedPreferences
✅ Interface intuitiva e responsiva
✅ Notificações de lembretes
✅ Organização por categorias
✅ Busca e filtro de tarefas
✅ Sincronização automática

## 🛠️ Tecnologias

- **Android SDK** (API 21+)
- **Java/Kotlin**
- **Room Database** para persistência
- **RecyclerView** para listas
- **Material Design 3**
- **SharedPreferences** para configurações

## 📂 Estrutura do Projeto

```
todo-list-app/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/todolist/
│   │   │   │   ├── MainActivity.java
│   │   │   │   ├── models/
│   │   │   │   │   └── Task.java
│   │   │   │   ├── database/
│   │   │   │   │   ├── TaskDatabase.java
│   │   │   │   │   └── TaskDao.java
│   │   │   │   ├── adapters/
│   │   │   │   │   └── TaskAdapter.java
│   │   │   │   └── utils/
│   │   │   │       └── PreferenceManager.java
│   │   │   └── res/
│   │   │       ├── layout/
│   │   │       │   ├── activity_main.xml
│   │   │       │   ├── item_task.xml
│   │   │       │   └── dialog_add_task.xml
│   │   │       ├── drawable/
│   │   │       ├── values/
│   │   │       └── menu/
│   │   └── test/
│   └── build.gradle
├── build.gradle
└── settings.gradle
```

## 🚀 Como Começar

### Pré-requisitos
- Android Studio Arctic Fox ou superior
- SDK Android 21+
- Java 8 ou Kotlin 1.5+

### Instalação

1. Clone o repositório
```bash
git clone https://github.com/kauabispo331/todo-list-app.git
cd todo-list-app
```

2. Abra no Android Studio
```bash
open -a "Android Studio" .
```

3. Configure um emulador ou conecte um dispositivo

4. Execute o projeto
```bash
./gradlew build
./gradlew installDebug
```

## 📱 Funcionalidades Detalhadas

### Adicionar Tarefa
- Clique no botão FAB (+)
- Preencha o título e descrição
- Defina prioridade e data de vencimento
- Clique em "Salvar"

### Editar Tarefa
- Toque na tarefa para abrir detalhes
- Clique no ícone de editar
- Altere as informações
- Salve as alterações

### Deletar Tarefa
- Deslize a tarefa para a esquerda
- Clique no ícone de lixo
- Confirme a exclusão

### Marcar como Concluída
- Toque na checkbox ao lado da tarefa
- A tarefa será riscada
- Pode ser desmarcada a qualquer momento

## 🎨 Interface

### Cores (Material Design 3)
- **Primária:** #6200EE (Roxo)
- **Secundária:** #03DAC6 (Ciano)
- **Terciária:** #FF0266 (Rosa)
- **Fundo:** #FFFFFF (Branco)

### Componentes
- Material Buttons
- Material Cards
- Material Text Fields
- Floating Action Button (FAB)
- Bottom App Bar

## 💾 Armazenamento Local

### Room Database
```java
@Entity(tableName = "tasks")
public class Task {
    @PrimaryKey(autoGenerate = true)
    public int id;
    
    @ColumnInfo(name = "title")
    public String title;
    
    @ColumnInfo(name = "description")
    public String description;
    
    @ColumnInfo(name = "priority")
    public int priority; // 1-5
    
    @ColumnInfo(name = "completed")
    public boolean completed;
    
    @ColumnInfo(name = "due_date")
    public long dueDate;
    
    @ColumnInfo(name = "created_at")
    public long createdAt;
}
```

## 🔍 Busca e Filtro

- Busca por título
- Filtro por prioridade
- Filtro por status (Ativo/Concluído)
- Ordenação por data ou prioridade

## 📊 Estatísticas

- Total de tarefas
- Tarefas concluídas
- Tarefas pendentes
- Taxa de conclusão (%)

## 🔔 Notificações

- Lembretes para tarefas vencidas
- Notificação de tarefas próximas
- Sons e vibrações configuráveis

## 📋 Roadmap

- [ ] Sincronização com Firebase
- [ ] Categorias de tarefas
- [ ] Tarefas recorrentes
- [ ] Exportar para PDF
- [ ] Integração com Google Calendar
- [ ] Tema escuro
- [ ] Modo offline
- [ ] Sincronização em nuvem

## 🐛 Reportar Bugs

Encontre um bug? Crie uma [issue](https://github.com/kauabispo331/todo-list-app/issues) descrevendo o problema.

## 💡 Sugestões

Tem uma ideia? Abra uma [issue](https://github.com/kauabispo331/todo-list-app/issues) com o rótulo "enhancement".

## 📄 Licença

MIT License - veja [LICENSE](LICENSE) para detalhes.

---

**Desenvolvido com ❤️ por Kaua Bispo**

Este projeto é open source e está pronto para contribuições!