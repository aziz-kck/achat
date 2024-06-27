//package tn.esprit.rh.achat.services;
//
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import tn.esprit.rh.achat.entities.Stock;
//import tn.esprit.rh.achat.repositories.StockRepository;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(MockitoExtension.class)
//class StockServiceImplTest {
//    @Mock
//    private StockRepository stockRepository;
//
//    @InjectMocks
//    private StockServiceImpl stockService;
//
//    @Test
//     void testRetrieveAllStocks() {
//        // Given
//        Stock stock1 = new Stock("Stock 1", 100, 50);
//        Stock stock2 = new Stock("Stock 2", 150, 75);
//        List<Stock> mockStocks = Arrays.asList(stock1, stock2);
//
//        when(stockRepository.findAll()).thenReturn(mockStocks);
//        // When
//        List<Stock> result = stockService.retrieveAllStocks();
//        // Then
//        assertEquals(2, result.size());
//        assertEquals("Stock 1", result.get(0).getLibelleStock());
//        assertEquals(100, result.get(0).getQte());
//        assertEquals("Stock 2", result.get(1).getLibelleStock());
//        assertEquals(150, result.get(1).getQte());
//
//        verify(stockRepository).findAll();
//    }
//
//    @Test
//     void testAddStock() {
//        // Given
//        Stock newStock = new Stock("Nouveau Stock", 200, 100);
//        when(stockRepository.save(newStock)).thenReturn(newStock);
//        // When
//        Stock result = stockService.addStock(newStock);
//        // Then
//        assertEquals("Nouveau Stock", result.getLibelleStock());
//        assertEquals(200, result.getQte());
//        assertEquals(100, result.getQteMin());
//
//        verify(stockRepository).save(newStock);
//    }
//
//    @Test
//     void testDeleteStock() {
//        // Given
//        Long stockId = 1L;
//        // When
//        stockService.deleteStock(stockId);
//        // Then
//        verify(stockRepository).deleteById(stockId);
//    }
//
//    @Test
//     void testUpdateStock() {
//        // Given
//        Stock existingStock = new Stock("Stock existant", 300, 150);
//        when(stockRepository.save(existingStock)).thenReturn(existingStock);
//        // When
//        Stock result = stockService.updateStock(existingStock);
//        // Then
//        assertEquals("Stock existant", result.getLibelleStock());
//        assertEquals(300, result.getQte());
//        assertEquals(150, result.getQteMin());
//
//        verify(stockRepository).save(existingStock);
//    }
//
//    @Test
//     void testRetrieveStock() {
//        // Given
//        Long stockId = 1L;
//        Stock mockStock = new Stock("Stock mock", 250, 125);
//        when(stockRepository.findById(stockId)).thenReturn(Optional.of(mockStock));
//        // When
//        Stock result = stockService.retrieveStock(stockId);
//        // Then
//        assertEquals("Stock mock", result.getLibelleStock());
//        assertEquals(250, result.getQte());
//        assertEquals(125, result.getQteMin());
//
//        verify(stockRepository).findById(stockId);
//    }
//
//    @Test
//     void testRetrieveStatusStock() {
//        // Given
//        Stock stock1 = new Stock("Stock 1", 40, 50); // Stock en rouge
//        Stock stock2 = new Stock("Stock 2", 80, 100); // Stock OK
//        List<Stock> mockStocks = Arrays.asList(stock1, stock2);
//
//        when(stockRepository.retrieveStatusStock()).thenReturn(mockStocks);
//
//        // When
//        String statusMessage = stockService.retrieveStatusStock();
//
//        // Then
//        assertTrue(statusMessage.contains("Stock 1"));
//        assertTrue(statusMessage.contains("Stock 2"));
//        assertTrue(statusMessage.contains("40")); // Quantité en rouge pour Stock 1
//
//        verify(stockRepository).retrieveStatusStock();
//    }
//}