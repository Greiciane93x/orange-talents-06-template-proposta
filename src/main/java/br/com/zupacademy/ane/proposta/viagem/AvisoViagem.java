package br.com.zupacademy.ane.proposta.viagem;

import br.com.zupacademy.ane.proposta.cadastroproposta.Proposta;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class AvisoViagem {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long idAvisoViagem;
    @Column(nullable = false)
    private LocalDate instanteViagem;
    @Column(nullable = false)
    private String ipClient;
    @Column(nullable = false)
    private String userAgent;
    @Column(nullable = false)
    private String destinoViagem;
    @Column(nullable = false)
    private LocalDate terminoViagem;

    @ManyToOne
    private Proposta proposta;

    public AvisoViagem(LocalDate instanteViagem, String ipClient, String userAgent,
                       String destinoViagem, LocalDate terminoViagem, Proposta proposta) {
        this.instanteViagem = instanteViagem;
        this.ipClient = ipClient;
        this.userAgent = userAgent;
        this.destinoViagem = destinoViagem;
        this.terminoViagem = terminoViagem;
        this.proposta = proposta;
    }

    public AvisoViagem(String destino, LocalDate validoAte) {
        this.destinoViagem = destino;
        this.terminoViagem = validoAte;
    }

    public Long getIdAvisoViagem() {
        return idAvisoViagem;
    }

    public LocalDate getInstanteViagem() {
        return instanteViagem;
    }

    public String getIpClient() {
        return ipClient;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getDestinoViagem() {
        return destinoViagem;
    }

    public LocalDate getTerminoViagem() {
        return terminoViagem;
    }

    public Proposta getProposta() {
        return proposta;
    }
}
