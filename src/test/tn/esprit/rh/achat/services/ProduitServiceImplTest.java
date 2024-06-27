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
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
     void testAddProduit() {
        // Given
        Produit produitToAdd = new Produit();
        produitToAdd.setCodeProduit("P001");
        produitToAdd.setLibelleProduit("Produit Test");
        produitToAdd.setPrix(100.0f);
        produitToAdd.setDateCreation(new Date());
        produitToAdd.setDateDerniereModification(new Date());

        // When
        produitService.addProduit(produitToAdd);

        // Then
        verify(produitRepository).save(produitToAdd);
    }

    @Test
     void testDeleteProduit() {
        // Given
        Long produitIdToDelete = 1L;

        // When
        produitService.deleteProduit(produitIdToDelete);

        // Then
        verify(produitRepository).deleteById(produitIdToDelete);
    }

    @Test
     void testUpdateProduit() {
        // Given
        Produit produitToUpdate = new Produit();
        produitToUpdate.setIdProduit(1L);
        produitToUpdate.setCodeProduit("P001");
        produitToUpdate.setLibelleProduit("Produit Mis à Jour");
        produitToUpdate.setPrix(150.0f);
        produitToUpdate.setDateDerniereModification(new Date());

        // When
        produitService.updateProduit(produitToUpdate);

        // Then
        verify(produitRepository).save(produitToUpdate);
    }

    @Test
     void testRetrieveProduit() {
        // Given
        Long produitId = 1L;
        Produit expectedProduit = new Produit();
        expectedProduit.setIdProduit(produitId);
        expectedProduit.setCodeProduit("P001");
        expectedProduit.setLibelleProduit("Produit Test");
        expectedProduit.setPrix(100.0f);
        expectedProduit.setDateCreation(new Date());
        expectedProduit.setDateDerniereModification(new Date());
        when(produitRepository.findById(produitId)).thenReturn(Optional.of(expectedProduit));

        // When
        Produit retrievedProduit = produitService.retrieveProduit(produitId);

        // Then
        assertEquals(expectedProduit, retrievedProduit);
    }

    @Test
     void testAssignProduitToStock() {
        // Given
        Long produitId = 1L;
        Long stockId = 1L;
        Produit produit = new Produit();
        Stock stock = new Stock();
        stock.setIdStock(stockId);

        when(produitRepository.findById(produitId)).thenReturn(Optional.of(produit));
        when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));

        // When
        produitService.assignProduitToStock(produitId, stockId);

        // Then
        verify(produitRepository).save(produit);
        assertEquals(stock, produit.getStock());
    }
}