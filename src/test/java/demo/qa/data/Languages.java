package demo.qa.data;

public enum Languages {
    en("GitHub Docs"),
    ru("Документация по GitHub"),
    es("Documentación de GitHub");

    public final String title;
    Languages(String title) {
        this.title = title;
    }
}
