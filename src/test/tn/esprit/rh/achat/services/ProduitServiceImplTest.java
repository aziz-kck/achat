package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.StockRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProduitServiceImplTest {

    @Mock
    private ProduitRepository produitRepository;

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private ProduitServiceImpl produitService;

    @Test
    void testRetrieveAllProduits() {
        // Given
        List<Produit> produits = Arrays.asList(
                new Produit(1L, "CODE1", "Produit 1", 100.0f, new Date(), new Date(), null, null, null),
                new Produit(2L, "CODE2", "Produit 2", 150.0f, new Date(), new Date(), null, null, null)
        );
        when(produitRepository.findAll()).thenReturn(produits);

        // When
        List<Produit> retrievedProduits = produitService.retrieveAllProduits();

        // Then
        assertEquals(2, retrievedProduits.size());
        assertEquals("CODE1", retrievedProduits.get(0).getCodeProduit());
        assertEquals("Produit 2", retrievedProduits.get(1).getLibelleProduit());
        verify(produitRepository, times(1)).findAll();
    }

    @Test
    void testAddProduit() {
        // Given
        Produit produitToAdd = new Produit(null, "CODE3", "Produit 3", 200.0f, new Date(), new Date(), null, null, null);
        when(produitRepository.save(any(Produit.class))).thenAnswer(invocation -> {
            Produit p = invocation.getArgument(0);
            p.setIdProduit(3L); // Simulate saving with ID
            return p;
        });

        // When
        Produit addedProduit = produitService.addProduit(produitToAdd);

        // Then
        assertNotNull(addedProduit);
        assertEquals(3L, addedProduit.getIdProduit());
        assertEquals("CODE3", addedProduit.getCodeProduit());
        verify(produitRepository, times(1)).save(any(Produit.class));
    }

    @Test
    void testDeleteProduit() {
        // Given
        Long produitIdToDelete = 1L;

        // When
        produitService.deleteProduit(produitIdToDelete);

        // Then
        verify(produitRepository, times(1)).deleteById(produitIdToDelete);
    }

    @Test
    void testUpdateProduit() {
        // Given
        Produit existingProduit = new Produit(1L, "CODE1", "Produit 1", 100.0f, new Date(), new Date(), null, null, null);
        when(produitRepository.save(any(Produit.class))).thenReturn(existingProduit);

        // When
        Produit updatedProduit = produitService.updateProduit(existingProduit);

        // Then
        assertNotNull(updatedProduit);
        assertEquals("CODE1", updatedProduit.getCodeProduit());
        verify(produitRepository, times(1)).save(any(Produit.class));
    }

    @Test
    void testRetrieveProduit() {
        // Given
        Long produitIdToRetrieve = 1L;
        Produit produitToRetrieve = new Produit(produitIdToRetrieve, "CODE1", "Produit 1", 100.0f, new Date(), new Date(), null, null, null);
        when(produitRepository.findById(produitIdToRetrieve)).thenReturn(Optional.of(produitToRetrieve));

        // When
        Produit retrievedProduit = produitService.retrieveProduit(produitIdToRetrieve);

        // Then
        assertNotNull(retrievedProduit);
        assertEquals(produitIdToRetrieve, retrievedProduit.getIdProduit());
        assertEquals("CODE1", retrievedProduit.getCodeProduit());
        verify(produitRepository, times(1)).findById(produitIdToRetrieve);
    }

    @Test
    void testAssignProduitToStock() {
        // Given
        Long idProduit = 1L;
        Long idStock = 1L;
        Produit produit = new Produit(idProduit, "CODE1", "Produit 1", 100.0f, new Date(), new Date(), null, null, null);
        Stock stock = new Stock(idStock, "Stock 1");

        // Mock repository behavior
        when(produitRepository.findById(idProduit)).thenReturn(Optional.of(produit));
        when(stockRepository.findById(idStock)).thenReturn(Optional.of(stock));

        // When
        produitService.assignProduitToStock(idProduit, idStock);

        // Then
        assertEquals(stock, produit.getStock());
        verify(produitRepository, times(1)).save(produit);
    }
}
