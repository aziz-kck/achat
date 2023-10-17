package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.CategorieFournisseur;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.repositories.FournisseurRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FournisseurServiceImplTest {

    @Mock
    public FournisseurRepository fournisseurRepository;

    @InjectMocks
    public FournisseurServiceImpl fournisseurService;

    @Test
    public void testRetrieveFournisseur() {
        // Create a sample Fournisseur object
        Fournisseur fournisseur = new Fournisseur("f1", "123", CategorieFournisseur.ORDINAIRE);
        fournisseur.setIdFournisseur(1L); // Set the ID

        // Mock the behavior of the fournisseurRepository
        Mockito.when(fournisseurRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(fournisseur));

        // Create an instance of FournisseurServiceImpl (you may need to inject the repository)
        fournisseurService = new FournisseurServiceImpl(fournisseurRepository);

        // Call the method to be tested
        Fournisseur fournisseur1 = fournisseurService.retrieveFournisseur(1L);

        // Assert that the result is not null
        Assertions.assertNotNull(fournisseur1);
    }
}