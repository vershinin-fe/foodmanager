package su.vfe.foodmanager.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "items")
@Access(value = AccessType.FIELD)
public class Item extends AbstractNamedEntity {

    //TODO: Think about units of measurement
    @Column(name = "quantity")
    @Range(min = 0, max = 100)
    private int quantity;

    @Column(name = "description")
    @NotBlank
    @Size(min = 2, max = 250)
    private String description;

    @Column(name = "price")
    @Range(min = 0, max = 1000000)
    private int price;

    @Column(name = "closed")
    private boolean closed;

    @NotNull
    @Column(name = "createdate")
    private LocalDateTime createDate;

    @Column(name = "closedate")
    private LocalDateTime closeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Family family;

    public Item() {
    }

    public Item(Integer id, String name, int quantity) {
        this(id, name, quantity, null);
    }

    public Item(Integer id, String name, int quantity, String description) {
        this(id, name, quantity, description, 0, false, null, null);
    }

    public Item(Integer id, String name, int quantity, String description, int price, boolean closed, LocalDateTime createDate, LocalDateTime closeDate) {
        super(id, name);
        this.quantity = quantity;
        this.description = description;
        this.price = price;
        this.closed = closed;
        this.createDate = createDate;
        this.closeDate = closeDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    @Override
    public String toString() {
        return "Item{" +
                "quantity=" + quantity +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", closed=" + closed +
                ", createDate=" + createDate +
                ", closeDate=" + closeDate +
                ", family=" + family +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}