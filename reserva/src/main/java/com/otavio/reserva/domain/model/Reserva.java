package com.otavio.reserva.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false, referencedColumnName = "id")
    private Sala sala;
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false, referencedColumnName = "id")
    private Usuario usuario;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(id, reserva.id) && Objects.equals(dataHora, reserva.dataHora) && Objects.equals(sala, reserva.sala) && Objects.equals(usuario, reserva.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataHora, sala, usuario);
    }
}
