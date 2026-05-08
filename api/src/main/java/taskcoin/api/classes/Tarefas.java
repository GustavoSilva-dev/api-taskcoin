package taskcoin.api.classes;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "tarefas")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Tarefas {
}
