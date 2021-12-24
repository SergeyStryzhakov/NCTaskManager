package ua.edu.sumdu.j2se.stryzhakov.tasks.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.sumdu.j2se.stryzhakov.tasks.model.Model;
import ua.edu.sumdu.j2se.stryzhakov.tasks.view.Viewable;

public class SaveController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(SaveController.class);
    private Viewable view;
    private Model model;

    public SaveController(Viewable view, Model model) {
        this.view = view;
        this.model = model;
        logger.info("Start save controller");
    }

    @Override
    public void start() {
        model.setChanged(false);
        int userChoice = view.show("");
        switch (userChoice) {
            case 1:
                model.save(model.getList());
                ControllerFactory.selectController(Action.MAIN).start();
                break;
            case 2:
                model.save(model.getList());
                logger.info("Exit with saving");
                System.exit(0);
            case 3:
                logger.info("Exit without saving");
                System.exit(0);
        }
    }
}
