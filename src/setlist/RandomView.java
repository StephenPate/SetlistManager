package setlist;

/**
 * Catalog view class that creates a randomized list of all the songs in the Catalog
 */
public class RandomView implements CatalogView{
    Catalog randomCatalog;

    @Override
    public void Sort(Catalog catalog) {
        randomCatalog = catalog.duplicate();
        randomCatalog.Shuffle();
    }

    @Override
    public Catalog getList() {
        return randomCatalog;
    }
}
