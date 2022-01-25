package by.academy.controller;

import by.academy.dto.RecordCommand;
import by.academy.service.RecordService;
import by.academy.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@Controller
public class RecordController {
    @Autowired
    private RecordService recordService;

    @Autowired
    @Qualifier("recordValidatorImpl")
    private Validator<RecordCommand> recordValidator;

    @PostMapping("/create_record.do")
    public String createRecord(@ModelAttribute("recordCommand") RecordCommand recordCommand, HttpSession session) throws IOException {
        Optional<String> validationError = recordValidator.validate(recordCommand);
        if (validationError.isPresent()) {
            session.setAttribute("record_error", validationError.get());
        } else {
            recordService.saveNewRecord(recordCommand);
        }
        return "redirect:/my_page.do";
    }

    @GetMapping("/{recordId}/delete_record.do")
    public String deleteRecord(@PathVariable("recordId") String recordId) {
        recordService.deleteRecord(recordId);
        return "redirect:/my_page.do";
    }
}
