package br.edu.materdei.stock.aspect;

import br.edu.materdei.tas.purchase.entity.PurchaseEntity;
import br.edu.materdei.tas.purchase.service.PurchaseService;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;

import br.edu.materdei.tas.stock.entity.StockEntity;
import br.edu.materdei.tas.stock.service.StockService;

import br.edu.materdei.tas.sale.entity.InvoiceEntity;
import br.edu.materdei.tas.sale.service.InvoiceService;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author brunofontana
 */
@Aspect
@Component
public class StockAspect {

    @Autowired
    private StockService stockService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private InvoiceService invoiceService;

    /* Compra */
    @AfterReturning(pointcut = "execution( * br.edu.materdei.tas.purchase.service.PurchaseService.save(..))")
    public void savePurchase(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        PurchaseEntity purchase = (PurchaseEntity) args[0];

        purchase.getItems().forEach(item -> {
            StockEntity stock = new StockEntity();

            // Alimenta o estoque com os dados dos itens da compra
            stock.setProduct(item.getProduct());
            stock.setQuantity(item.getQuantity());
            stock.setHistory("Movimento de entrada originado pela compra " + purchase.getCode());

            // Grava a movimentação de estoque
            this.stockService.save(stock);
        });
    }

    @Before("execution( * br.edu.materdei.tas.purchase.service.PurchaseService.delete(..))")
    public void reversePurchase(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Integer id = (Integer) args[0];

        try {
            /**
             * FindById: Utiliza o EntityManager.find, ou seja, busca no banco
             * de dados Neste caso colocamos o Before, pois AfterReturn ocorre
             * depois da exclusão, ou seja depois que o registro já não existe.
             */
            PurchaseEntity purchase = this.purchaseService.findById(id);

            purchase.getItems().forEach(item -> {

                StockEntity stock = new StockEntity();

                // Alimento o estoque com os dados dos itens da compra, fazendo um estorno
                stock.setProduct(item.getProduct());
                stock.setQuantity(item.getQuantity() * -1);
                stock.setHistory("Movimento de saída originado pelo estorno da compra " + purchase.getCode());

                this.stockService.save(stock);
            });

        } catch (ResourceNotFoundException ex) {
            Logger.getLogger(StockAspect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* Compra */

    /* Venda */
    @AfterReturning(pointcut = "execution( * br.edu.materdei.tas.sale.service.InvoiceService.save(..))")
    public void saveInvoice(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        InvoiceEntity invoice = (InvoiceEntity) args[0];

        invoice.getSalesOrder().getItems().forEach(item -> {
            StockEntity stock = new StockEntity();

            // Alimenta o estoque com os dados dos itens da venda
            stock.setProduct(item.getProduct());
            stock.setQuantity(item.getQuantity() * -1);
            stock.setHistory("Movimento de saída originado pela venda " + invoice.getCode());

            // Grava a movimentação de estoque
            this.stockService.save(stock);
        });
    }

    @Around("execution(* br.edu.materdei.tas.sale.service.InvoiceService.delete(..))")
    public void reverseInvoice(ProceedingJoinPoint joinPoint) {
        // Antes de excluir a venda...
        Object[] args = joinPoint.getArgs();
        Integer id = (Integer) args[0];

        InvoiceEntity invoice = null;

        try {

            invoice = this.invoiceService.findById(id);

        } catch (ResourceNotFoundException ex) {
            Logger.getLogger(StockAspect.class.getName()).log(Level.SEVERE, null,
                    "O processo de movimento foi abortado, pois não foi encontrada uma venda com o ID " + id);
        }

        // Procedo com a exclusão
        try {

            joinPoint.proceed();

        } catch (Throwable ex) {
            Logger.getLogger(StockAspect.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Depois da exclusão...
        if (invoice != null) {
            String codeInvoice = invoice.getCode();

            invoice.getSalesOrder().getItems().forEach(item -> {

                StockEntity estoque = new StockEntity();
                estoque.setProduct(item.getProduct());
                estoque.setQuantity(item.getQuantity());
                estoque.setHistory("Movimento de entrada originado pelo estorno da venda " + codeInvoice);

                this.stockService.save(estoque);
            });
        }
    }
    /* Venda */
}
