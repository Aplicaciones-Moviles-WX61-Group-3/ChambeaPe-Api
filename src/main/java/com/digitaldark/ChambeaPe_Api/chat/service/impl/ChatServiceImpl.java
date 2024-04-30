package com.digitaldark.ChambeaPe_Api.chat.service.impl;

import com.digitaldark.ChambeaPe_Api.chat.dto.ChatDTO;
import com.digitaldark.ChambeaPe_Api.chat.dto.request.ChatRequestDTO;
import com.digitaldark.ChambeaPe_Api.chat.dto.request.MessageRequestDTO;
import com.digitaldark.ChambeaPe_Api.chat.dto.response.ChatResponseDTO;
import com.digitaldark.ChambeaPe_Api.chat.dto.response.MessageResponseDTO;
import com.digitaldark.ChambeaPe_Api.chat.model.ChatEntity;
import com.digitaldark.ChambeaPe_Api.chat.model.MessageEntity;
import com.digitaldark.ChambeaPe_Api.chat.repository.ChatRepository;
import com.digitaldark.ChambeaPe_Api.chat.repository.MessageRepository;
import com.digitaldark.ChambeaPe_Api.chat.service.ChatService;
import com.digitaldark.ChambeaPe_Api.shared.DateTimeEntity;
import com.digitaldark.ChambeaPe_Api.shared.exception.ResourceNotFoundException;
import com.digitaldark.ChambeaPe_Api.shared.exception.ValidationException;
import com.digitaldark.ChambeaPe_Api.user.dto.EmployerDTO;
import com.digitaldark.ChambeaPe_Api.user.dto.WorkerDTO;
import com.digitaldark.ChambeaPe_Api.user.dto.response.UserResponseDTO;
import com.digitaldark.ChambeaPe_Api.user.model.EmployerEntity;
import com.digitaldark.ChambeaPe_Api.user.model.WorkerEntity;
import com.digitaldark.ChambeaPe_Api.user.repository.EmployerRepository;
import com.digitaldark.ChambeaPe_Api.user.repository.WorkerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DateTimeEntity dateTimeEntity;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private WorkerRepository workerRepository;

    public ChatResponseDTO createChat(ChatRequestDTO chatRequestDTO) {
        //Validations
        int employerId = chatRequestDTO.getEmployerId();
        int workerId = chatRequestDTO.getWorkerId();

        if(!employerRepository.existsById(employerId)) {
            throw new ResourceNotFoundException("Employer not found");
        }
        if(!workerRepository.existsById(workerId)) {
            throw new ResourceNotFoundException("Worker not found");
        }
        if(chatRepository.existsByEmployer(employerRepository.findById(employerId)) && chatRepository.existsByWorker(workerRepository.findById(workerId))) {
            throw new ValidationException("Chat already exists");
        }
        //

        System.out.println("chatRequestDTO: " + chatRequestDTO);

        ChatEntity chatEntity = new ChatEntity();

        System.out.println("chatEntity: " + chatEntity);

        chatEntity.setCreationTime(dateTimeEntity.currentTime());
        chatEntity.setDateCreated(dateTimeEntity.currentTime());
        chatEntity.setDateUpdated(dateTimeEntity.currentTime());
        chatEntity.setIsActive((byte) 1);

        chatEntity.setEmployer(employerRepository.findById(employerId));
        chatEntity.setWorker(workerRepository.findById(workerId));

        chatRepository.save(chatEntity);

        //Generando el chatResponseDTO
        ChatResponseDTO chatResponseDTO = modelMapper.map(chatEntity, ChatResponseDTO.class);

        //Generando el WorkerDTO
        chatResponseDTO.setWorker(getWorkerDTO(chatEntity.getWorker().getId()));

        //Generando el EmployerDTO
        chatResponseDTO.setEmployer(getEmployerDTO(chatEntity.getEmployer().getId()));

        return chatResponseDTO;
    }

    public MessageResponseDTO createMessage(MessageRequestDTO messageRequestDTO, int chatId) {
        //Validations
        if(!chatRepository.existsById(chatId)) {
            throw new ResourceNotFoundException("Chat not found");
        }

        WorkerEntity worker = workerRepository.findById(messageRequestDTO.getSendById());
        EmployerEntity employer = employerRepository.findById(messageRequestDTO.getSendById());

        if(!chatRepository.existsByIdAndEmployer(chatId, employer) && !chatRepository.existsByIdAndWorker(chatId, worker)) {
            throw new ValidationException("This user isn't included in this chat");
        }

        MessageEntity messageEntity = modelMapper.map(messageRequestDTO, MessageEntity.class);
        messageEntity.setTime(dateTimeEntity.currentTime());
        messageEntity.setDateCreated(dateTimeEntity.currentTime());
        messageEntity.setDateUpdated(dateTimeEntity.currentTime());
        messageEntity.setIsActive((byte) 1);
        messageEntity.setChatEntity(chatRepository.findById(chatId));

        messageRepository.save(messageEntity);

        return modelMapper.map(messageEntity, MessageResponseDTO.class);
    }

    public List<ChatDTO> getChatsByUserId(int id) {
        if(!employerRepository.existsById(id) && !workerRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found");
        }

        if(employerRepository.existsById(id)) {//Quiero ver los chats de un employer y worker, y setear la List<MessageResponseDTO>
            List<ChatEntity> chatEntities = chatRepository.findByEmployer(employerRepository.findById(id));

            List<ChatDTO> chatDTOs = chatEntities
                    .stream()
                    .map(chatEntity -> {
                        ChatDTO chatDTO = modelMapper.map(chatEntity, ChatDTO.class);

                        //Generando el userDTO
                        UserResponseDTO userResponseDTO = modelMapper.map(chatEntity.getWorker().getUser(), UserResponseDTO.class);
                        System.out.println("userResponseDTO: " + userResponseDTO);
                        chatDTO.setUser(userResponseDTO);


                        //Generando la List<MessageResponseDTO>
                        List<MessageResponseDTO> messageResponseDTOs = messageRepository.findByChatEntity(chatRepository.findById(chatEntity.getId()))
                                .stream()
                                .map(messageEntity -> modelMapper.map(messageEntity, MessageResponseDTO.class))
                                .collect(Collectors.toList());

                        chatDTO.setMessages(messageResponseDTOs);

                        return chatDTO;
                    })
                    .collect(Collectors.toList());

            return chatDTOs;
        }
        else if(workerRepository.existsById(id)) {

            List<ChatEntity> chatEntities = chatRepository.findByWorker(workerRepository.findById(id));

            List<ChatDTO> chatDTOs = chatEntities
                    .stream()
                    .map(chatEntity -> {
                        ChatDTO chatDTO = modelMapper.map(chatEntity, ChatDTO.class);

                        //Generando el userDTO
                        UserResponseDTO userResponseDTO = modelMapper.map(chatEntity.getEmployer().getUser(), UserResponseDTO.class);
                        System.out.println("userResponseDTO: " + userResponseDTO);
                        chatDTO.setUser(userResponseDTO);

                        //Generando la List<MessageResponseDTO>
                        List<MessageResponseDTO> messageResponseDTOs = messageRepository.findByChatEntity(chatRepository.findById(chatEntity.getId()))
                                .stream()
                                .map(messageEntity -> modelMapper.map(messageEntity, MessageResponseDTO.class))
                                .collect(Collectors.toList());

                        chatDTO.setMessages(messageResponseDTOs);

                        return chatDTO;
                    })
                    .collect(Collectors.toList());

            return chatDTOs;
        }
        else {
            throw new ResourceNotFoundException("Chats by user not found");
        }
    }

    private WorkerDTO getWorkerDTO(int id) {
        WorkerEntity workerEntity = workerRepository.findById(id);
        WorkerDTO workerDTO = modelMapper.map(workerEntity, WorkerDTO.class);
        modelMapper.map(workerEntity.getUser(), workerDTO);
        return workerDTO;
    }

    private EmployerDTO getEmployerDTO(int id) {
        EmployerEntity employerEntity = employerRepository.findById(id);
        EmployerDTO employerDTO = modelMapper.map(employerEntity, EmployerDTO.class);
        modelMapper.map(employerEntity.getUser(), employerDTO);
        return employerDTO;
    }

}
