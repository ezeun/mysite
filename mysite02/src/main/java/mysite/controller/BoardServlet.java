package mysite.controller;

import java.util.Map;

import jakarta.servlet.annotation.WebServlet;
import mysite.controller.action.board.ListAction;
import mysite.controller.action.board.ViewAction;

@WebServlet("/board")
public class BoardServlet extends ActionServlet {
	private static final long serialVersionUID = 1L;

	private Map<String, Action> mapAction = Map.of(
			"view", new ViewAction()
		);
	
	@Override
	protected Action getAction(String actionName) {
		return mapAction.getOrDefault(actionName, new ListAction());
	}

}
