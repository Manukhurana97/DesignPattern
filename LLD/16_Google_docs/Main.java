import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

// entities
class Document {
    private final String id;
    private final String userId;
    private String documentName;
    private List<DocumentElement> elements;

    Document(String userId, String documentName) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.documentName = documentName;
        elements = new CopyOnWriteArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void addElements(DocumentElement element) {
        elements.add(element);
    }

    public List<DocumentElement> getElements() {
        return elements;
    }
}



// strategy
interface DocumentElement {
    String render();
}


class Text implements DocumentElement {
    private final String text;

    public Text(String text) {
        this.text = text;
    }

    public String render() {
        return ("Rendering text: " + text);
    }
}

// decorator
abstract class TextDecorator implements DocumentElement {
    protected DocumentElement inner;

    public TextDecorator(DocumentElement inner) {
        this.inner = inner;
    }
}

class BoldText extends TextDecorator {

    public BoldText(DocumentElement inner) {
        super(inner);
    }

    public String render() {
        return "<B>"+inner.render()+"</B>";
    }
}

class Image implements DocumentElement {
    private final String url;

    public Image(String url) {
        this.url = url;
    }

    public String render() {
        return ("Rendering image: " + url);
    }
}

//---------------------------------------------------------


interface DocumentPersistence {
    void saveDocument(String id, String data);
}

class FileStorage implements DocumentPersistence {
    public void saveDocument(String id, String data) {
        System.out.printf("saving document with id: %s in file: %s\n", id, data);
    }
}

class DatabaseStorage implements DocumentPersistence {
    public void saveDocument(String id, String data) {
        System.out.printf("saving document with id: %s in db: %s\n", id, data);
    }
}


// service
class DocumentService {
    public static DocumentService INSTANCE;
    private final Map<String, Document> documents;
    private DocumentPersistence documentPersistance;

    public DocumentService() {
        this.documents = new ConcurrentHashMap<>();
        this.documentPersistance = new FileStorage();
    }

    public synchronized static DocumentService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DocumentService();
        }

        return INSTANCE;
    }

    public String createDocument(String userId, String docName) {
        Document document = new Document(userId, docName);
        documents.put(document.getId(), document);
        System.out.printf(" --- %s Document created --- \n", docName);
        return document.getId();
    }

    public void addText(String id, String text) {
        if(!documents.containsKey(id)) {
            System.out.println("Invalid document id");
            return;
        }

        documents.get(id).addElements(new Text(text));
        System.out.println("--- Text Added ---");
    }

    public void addText(String id, DocumentElement documentElement) {
        if(!documents.containsKey(id)) {
            System.out.println("Invalid document id");
            return;
        }

        documents.get(id).addElements(documentElement);
        System.out.println("--- Text Added ---");
    }

    public void addImage(String id, String url) {
        if(!documents.containsKey(id)) {
            System.out.println("Invalid document id");
            return;
        }

        documents.get(id).addElements(new Image(url));
        System.out.println("--- Image Added ---");
    }

    public void saveDocument(String id) {
        if(!documents.containsKey(id)) {
            System.out.println("Invalid document id");
            return;
        }

        List<DocumentElement> documentElements = documents.get(id).getElements();
        StringBuilder renderedText = new StringBuilder();
        for(DocumentElement element : documentElements) {
            renderedText.append(element.render());
        }

        documentPersistance.saveDocument(id, renderedText.toString());
    }
}

public class Main {
    public static void main() {
        DocumentService documentService = DocumentService.getInstance();

       String doc1_id = documentService.createDocument("User_1","Test_1");
       documentService.addText(doc1_id, "Hello world");
       documentService.addText(doc1_id, "img.jpg");
       documentService.addText(doc1_id, new BoldText(new Text("Hello all!!!")));
       documentService.saveDocument(doc1_id);

    }
}