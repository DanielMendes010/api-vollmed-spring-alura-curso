package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoConsulta {

    @Autowired
    private MedicoRespository medicoRespository;

    public void validar(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() == null) {
            return;
        }
        var medicoEstaAtivo = medicoRespository.findAtivoById(dados.idMedico());

        if (!medicoEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com medico inativo!");
        }
    }
}
