package tn.esprit.rh.achat.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;

@ExtendWith(MockitoExtension.class)
class SecteurActiviteServiceImplTest {
    @Mock
    private SecteurActiviteRepository secteurActiviteRepository;

    @InjectMocks
    private SecteurActiviteServiceImpl secteurActiviteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllSecteurActivite() {
        List<SecteurActivite> secteurActivites = List.of(
                new SecteurActivite(1L, "Code1", "Libelle1", null),
                new SecteurActivite(2L, "Code2", "Libelle2", null)
        );
        when(secteurActiviteRepository.findAll()).thenReturn(secteurActivites);

        List<SecteurActivite> result = secteurActiviteService.retrieveAllSecteurActivite();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(secteurActiviteRepository, times(1)).findAll();
    }

    @Test
    public void testAddSecteurActivite() {
        SecteurActivite secteurActivite = new SecteurActivite(1L, "Code1", "Libelle1", null);
        when(secteurActiviteRepository.save(any(SecteurActivite.class))).thenReturn(secteurActivite);

        SecteurActivite result = secteurActiviteService.addSecteurActivite(secteurActivite);

        assertNotNull(result);
        assertEquals("Code1", result.getCodeSecteurActivite());
        verify(secteurActiviteRepository, times(1)).save(any(SecteurActivite.class));
    }

    @Test
    public void testDeleteSecteurActivite() {
        Long id = 1L;

        secteurActiviteService.deleteSecteurActivite(id);

        verify(secteurActiviteRepository, times(1)).deleteById(id);
    }

    @Test
    public void testUpdateSecteurActivite() {
        SecteurActivite secteurActivite = new SecteurActivite(1L, "Code1", "Libelle1", null);
        when(secteurActiviteRepository.save(any(SecteurActivite.class))).thenReturn(secteurActivite);

        SecteurActivite result = secteurActiviteService.updateSecteurActivite(secteurActivite);

        assertNotNull(result);
        assertEquals("Code1", result.getCodeSecteurActivite());
        verify(secteurActiviteRepository, times(1)).save(any(SecteurActivite.class));
    }

    @Test
    public void testRetrieveSecteurActivite() {
        SecteurActivite secteurActivite = new SecteurActivite(1L, "Code1", "Libelle1", null);
        when(secteurActiviteRepository.findById(1L)).thenReturn(Optional.of(secteurActivite));

        SecteurActivite result = secteurActiviteService.retrieveSecteurActivite(1L);

        assertNotNull(result);
        assertEquals(1L, result.getIdSecteurActivite());
        verify(secteurActiviteRepository, times(1)).findById(1L);
    }

}