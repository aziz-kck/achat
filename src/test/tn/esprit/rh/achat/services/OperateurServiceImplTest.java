package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OperateurServiceImplTest {

    @Mock
    private OperateurRepository operateurRepository;

    @InjectMocks
    private OperateurServiceImpl operateurService;

    private Operateur operateur;

    @BeforeEach
    public void setUp() {
        operateur = new Operateur(1L, "nom", "prenom", "password", null);
    }

    @Test
    void testRetrieveOperateur() {
        // Given
        Long id = 1L;
        when(operateurRepository.findById(id)).thenReturn(Optional.of(operateur));

        // When
        Operateur retrievedOperateur = operateurService.retrieveOperateur(id);

        // Then
        assertNotNull(retrievedOperateur);
        assertEquals(id, retrievedOperateur.getIdOperateur());
        assertEquals("nom", retrievedOperateur.getNom());
        assertEquals("prenom", retrievedOperateur.getPrenom());
        assertEquals("password", retrievedOperateur.getPassword());
    }

    @Test
    void testAddOperateur() {
        // When
        Operateur savedOperateur = operateurService.addOperateur(operateur);

        // Then
        assertNotNull(savedOperateur);
        assertEquals("nom", savedOperateur.getNom());
        assertEquals("prenom", savedOperateur.getPrenom());
        assertEquals("password", savedOperateur.getPassword());
        verify(operateurRepository, times(1)).save(any(Operateur.class));
    }

    @Test
    void testUpdateOperateur() {
        // When
        operateur.setNom("nom2");
        Operateur updatedOperateur = operateurService.updateOperateur(operateur);

        // Then
        assertEquals("nom2", updatedOperateur.getNom());
        verify(operateurRepository, times(1)).save(any(Operateur.class));
    }

    @Test
    void testDeleteOperateur() {
        // When
        Long id = 1L;
        operateurService.deleteOperateur(id);

        // Then
        verify(operateurRepository, times(1)).deleteById(id);
    }

    @Test
    void testRetrieveAllOperateurs() {
        // Given
        List<Operateur> operateurs = new ArrayList<>();
        operateurs.add(operateur);
        when(operateurRepository.findAll()).thenReturn(operateurs);

        // When
        List<Operateur> retrievedOperateurs = operateurService.retrieveAllOperateurs();

        // Then
        assertEquals(1, retrievedOperateurs.size());
        assertEquals("nom", retrievedOperateurs.get(0).getNom());
        assertEquals("prenom", retrievedOperateurs.get(0).getPrenom());
        assertEquals("password", retrievedOperateurs.get(0).getPassword());
    }
}
