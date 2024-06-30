package common.beans;

import com.fiap.fastfood.common.beans.UseCaseBeanDeclaration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UseCaseBeanDeclarationTest {

    @InjectMocks
    private UseCaseBeanDeclaration declaration;

    @Test
    void productGatewayTest() {
        final var result = declaration.productUseCase();

        Assertions.assertNotNull(result);
    }
}