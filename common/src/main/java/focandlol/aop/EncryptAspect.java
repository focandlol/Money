package focandlol.aop;

import focandlol.annotation.Encrypt;
import focandlol.util.encrypt.EncryptComponent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Optional;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class EncryptAspect {

    private final EncryptComponent encryptComponent;


    @Before("execution(* focandlol.repository.*.save*(..))")
    public void encryptBeforeSave(JoinPoint joinPoint) throws IllegalAccessException {
        Object entity = joinPoint.getArgs()[0];
        processEncryption(entity, true);
    }

    @AfterReturning(pointcut = "execution(* focandlol.repository.*.find*(..))", returning = "result")
    public void decryptAfterFind(Object result) throws IllegalAccessException {
        if (result instanceof Optional) {
            Optional<?> optionalResult = (Optional<?>) result;
            if (optionalResult.isPresent()) {
                processEncryption(optionalResult.get(), false);
            }
        } else if (result instanceof Iterable) {
            for (Object entity : (Iterable<?>) result) {
                processEncryption(entity, false);
            }
        } else if (result != null) {
            processEncryption(result, false);
        }
    }

    private void processEncryption(Object entity, boolean encrypt) throws IllegalAccessException {
        if (entity == null) return;

        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Encrypt.class)) {
                field.setAccessible(true);
                Object value = field.get(entity);

                if (value != null && value instanceof String) {
                    String processedValue = encrypt
                            ? encryptComponent.encryptString((String) value)
                            : encryptComponent.decryptString((String) value);
                    field.set(entity, processedValue);
                }
            }
        }
    }
}

