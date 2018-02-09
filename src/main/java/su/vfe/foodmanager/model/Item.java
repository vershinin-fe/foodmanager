package su.vfe.foodmanager.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "items")
@Access(value = AccessType.FIELD)
public class Item {
    public static final int START_SEQ = 100;

    @Id
    @SequenceGenerator(name = "items_seq", sequenceName = "items_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "items_seq")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private double quantity;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private int price;

    @Column(name = "closed")
    private boolean closed;

    @Column(name = "createdate")
    private LocalDateTime createDate;

    @Column(name = "closedate")
    private LocalDateTime closeDate;

    public Item() {
    }

    public Item(Integer id, String name, double quantity) {
        this(id, name, quantity, null);
    }

    public Item(Integer id, String name, double quantity, String description) {
        this(id, name, quantity, description, 0, null, null);
    }

    public Item(Integer id, String name, double quantity, String description, int price, LocalDateTime createDate, LocalDateTime closeDate) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.closeDate = closeDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(LocalDateTime closeDate) {
        this.closeDate = closeDate;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", closed=" + closed +
                ", createDate=" + createDate +
                ", closeDate=" + closeDate +
                '}';
    }
}