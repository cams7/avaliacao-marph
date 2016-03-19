function updateListPage(growlId, tableId, dialogId, args) {
	if (args.validationFailed || !args.changed) {
		atualizaFomulario();
		return;
	}

	addMessage(growlId, args.message);

	PF(dialogId).hide();
	PF(tableId).filter();
}

function addMessage(growlId, message) {
	var severity = message.severity;
	severity = severity.substring(0, severity.indexOf(" "));
	severity = severity.toLowerCase();

	PF(growlId).renderMessage({
		"summary" : message.summary,
		"detail" : message.detail,
		"severity" : severity
	});
}