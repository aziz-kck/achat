package tn.esprit.rh.achat.services;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OperateurServiceImplTest {
    @Mock
    private OperateurRepository operateurRepository;

    @InjectMocks
    private OperateurServiceImpl operateurService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllOperateurs() {
        // Mock data
        Operateur operateur1 = new Operateur(1L, "Nom1", "Prenom1", "password1");
        Operateur operateur2 = new Operateur(2L, "Nom2", "Prenom2", "password2");
        List<Operateur> operateurs = Arrays.asList(operateur1, operateur2);

        // Mock repository method
        when(operateurRepository.findAll()).thenReturn(operateurs);

        // Call service method
        List<Operateur> result = operateurService.retrieveAllOperateurs();

        // Assertions
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Nom1", result.get(0).getNom());
        assertEquals("password2", result.get(1).getPassword());

        // Verify repository method call
        verify(operateurRepository, times(1)).findAll();
    }

    @Test
    public void testAddOperateur() {
        // Mock data
        Operateur operateur = new Operateur(1L, "Prenom1", "password1");

        // Mock repository save method
        when(operateurRepository.save(any(Operateur.class))).thenReturn(operateur);

        // Call service method
        Operateur result = operateurService.addOperateur(operateur);

        // Assertions
        assertNotNull(result);
        assertEquals("Prenom1", result.getPrenom());
        assertEquals("password1", result.getPassword());

        // Verify repository save method call
        verify(operateurRepository, times(1)).save(any(Operateur.class));
    }

    @Test
    public void testUpdateOperateur() {
        // Mock data
        Operateur operateur = new Operateur(1L, "Nom1", "Prenom1", "password1");

        // Mock repository save method
        when(operateurRepository.save(any(Operateur.class))).thenReturn(operateur);

        // Call service method
        Operateur result = operateurService.updateOperateur(operateur);

        // Assertions
        assertNotNull(result);
        assertEquals("Nom1", result.getNom());
        assertEquals("password1", result.getPassword());

        // Verify repository save method call
        verify(operateurRepository, times(1)).save(any(Operateur.class));
    }

    @Test
    public void testDeleteOperateur() {
        // Mock data
        Long operateurId = 1L;

        // Call service method
        operateurService.deleteOperateur(operateurId);

        // Verify repository deleteById method call
        verify(operateurRepository, times(1)).deleteById(operateurId);
    }
}