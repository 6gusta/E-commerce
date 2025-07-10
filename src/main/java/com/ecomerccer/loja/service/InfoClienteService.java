package com.ecomerccer.loja.service;


import com.ecomerccer.loja.model.InfoCliente;
import com.ecomerccer.loja.repository.InfoClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class InfoClienteService {

    private final InfoClienteRepository  infoClienteRepository;


    public InfoClienteService(InfoClienteRepository infoClienteRepository) {
        this.infoClienteRepository = infoClienteRepository;
    }

    public InfoCliente dadosCliente(    String nome,
                                        String telefone,
                                        String email,
                                        String endereco,
                                        String cidade,
                                        String estado,
                                        String cep,
                                        String complemento){

        InfoCliente infoCliente = new InfoCliente();
        infoCliente.setNome(nome);
        infoCliente.setTelefone(telefone);
        infoCliente.setEmail(email);
        infoCliente.setEndereco(endereco);
        infoCliente.setCidade(cidade);
        infoCliente.setEstado(estado);
        infoCliente.setCep(cep);
        infoCliente.setComplemento(complemento);

        return infoClienteRepository.save(infoCliente);




    }
}
