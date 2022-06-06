package com.residencia.ecommerce.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.residencia.ecommerce.dto.CepDTO;
import com.residencia.ecommerce.dto.EnderecoDTO;
import com.residencia.ecommerce.entity.Endereco;
import com.residencia.ecommerce.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	public List<Endereco> findAll() {
		return enderecoRepository.findAll();
	}

	public Endereco findById(Integer id) {
		return enderecoRepository.findById(id).get();
	}

	public EnderecoDTO findDTOById(Integer id) {
		return enderecoRepository.findById(id).isPresent()
				? converterEntidadeParaDTO(enderecoRepository.findById(id).get())
				: null;

	}

	public Endereco save(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

	public Endereco update(Endereco endereco, Integer id) {
		return enderecoRepository.save(endereco);
	}

	public void delete(Integer id) {
		Endereco endereco = enderecoRepository.findById(id).get();
		enderecoRepository.delete(endereco);
	}

	public EnderecoDTO saveDTO(EnderecoDTO enderecoDTO) {
		Endereco endereco = new Endereco();
		endereco = converterDTOParaEntidade(enderecoDTO);

		Endereco endereconovo = enderecoRepository.save(endereco);

		return converterEntidadeParaDTO(endereconovo);
	}

	public Endereco converterDTOParaEntidade(EnderecoDTO enderecoDTO) {
		Endereco endereco = new Endereco();

		endereco.setIdEndereco(enderecoDTO.getIdEndereco());
		endereco.setCep(enderecoDTO.getCep());
		endereco.setBairro(enderecoDTO.getBairro());
		endereco.setCidade(enderecoDTO.getCidade());
		endereco.setComplemento(enderecoDTO.getComplemento());
		endereco.setNumero(enderecoDTO.getNumero());
		endereco.setRua(enderecoDTO.getRua());
		endereco.setUf(enderecoDTO.getUf());

		return endereco;
	}

	public EnderecoDTO converterEntidadeParaDTO(Endereco endereco) {
		EnderecoDTO enderecoDTO = new EnderecoDTO();

		enderecoDTO.setBairro(endereco.getBairro());
		enderecoDTO.setCep(endereco.getCep());
		enderecoDTO.setCidade(endereco.getCidade());
		enderecoDTO.setComplemento(endereco.getComplemento());
		enderecoDTO.setRua(endereco.getRua());
		enderecoDTO.setUf(endereco.getUf());
		enderecoDTO.setNumero(endereco.getNumero());
		enderecoDTO.setIdEndereco(endereco.getIdEndereco());

		return enderecoDTO;
	}

	public Endereco CepDTOParaEndereco(CepDTO cepDTO) {
		Endereco endereco = new Endereco();
		endereco.setCep(cepDTO.getCep());
		endereco.setBairro(cepDTO.getBairro());
		endereco.setComplemento(cepDTO.getComplemento());
		endereco.setUf(cepDTO.getUf());
		endereco.setCidade(cepDTO.getLocalidade());
		endereco.setRua(cepDTO.getLogradouro());

		return endereco;
	}
	
	public EnderecoDTO CepDTOParaEnderecoDTO(CepDTO cepDTO) {
		EnderecoDTO endereco = new EnderecoDTO();
		endereco.setCep(cepDTO.getCep());
		endereco.setBairro(cepDTO.getBairro());
		endereco.setComplemento(cepDTO.getComplemento());
		endereco.setUf(cepDTO.getUf());
		endereco.setCidade(cepDTO.getLocalidade());
		endereco.setRua(cepDTO.getLogradouro());

		return endereco;
	}

	public EnderecoDTO saveCep(String cep) {
		return converterEntidadeParaDTO(enderecoRepository.save(CepDTOParaEndereco(consultarCepDTO(cep))));
	}

	public CepDTO consultarCepDTO(String cep) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "https://viacep.com.br/ws/{cep}/json";
		Map<String, String> params = new HashMap<String, String>();
		params.put("cep", cep);

		CepDTO cepDTO = restTemplate.getForObject(uri, CepDTO.class, params);
		return cepDTO;

	}
}
