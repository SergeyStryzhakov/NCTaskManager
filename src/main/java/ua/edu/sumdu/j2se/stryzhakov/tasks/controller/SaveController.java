package ua.edu.sumdu.j2se.stryzhakov.tasks.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.sumdu.j2se.stryzhakov.tasks.model.Model;
import ua.edu.sumdu.j2se.stryzhakov.tasks.view.Viewable;

/**
 * Check changes in task list and save it.
 */
public class SaveController implements Controller {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(SaveController.class);
    private final Viewable view;
    private final Model model;

    public SaveController(final Viewable view, final Model model) {
        this.view = view;
        this.model = model;
        LOGGER.info("Start save controller");
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
                LOGGER.info("Exit with saving");
                System.exit(0);
            case 3:
                LOGGER.info("Exit without saving");
                System.exit(0);
        }
    }
}
