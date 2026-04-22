package by.nurbolat.smartinventoryapplication.event;


import by.nurbolat.smartinventoryapplication.entity.inventory.Stock;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class LowStockEvent extends ApplicationEvent {
    private final Stock stock;

    public LowStockEvent(Object source, Stock stock) {
        super(source);
        this.stock = stock;
    }
}