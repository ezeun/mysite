package mysite.controller;

import java.util.Map;

import jakarta.servlet.annotation.WebServlet;
import mysite.controller.action.board.DeleteAction;
import mysite.controller.action.board.ListAction;
import mysite.controller.action.board.ViewAction;
import mysite.controller.action.board.WriteAction;
import mysite.controller.action.board.WriteFormAction;

@WebServlet("/board")
public class BoardServlet extends ActionServlet {
	private static final long serialVersionUID = 1L;

	private Map<String, Action> mapAction = Map.of(
			"view", new ViewAction(),
			"writeform", new WriteFormAction(),
			"write", new WriteAction(),
			"delete", new DeleteAction()
		);
	
	@Override
	protected Action getAction(String actionName) {
		return mapAction.getOrDefault(actionName, new ListAction());
	}

}
