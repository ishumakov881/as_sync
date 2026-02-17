Всегда разделяй на Screen / Content / Widgets / Dialog.

Никогда не открывай Dialog внутри UI компонента.

UI компонент не знает про ViewModel.

Все действия — через callbacks.

State делится на:

Screen state (из ViewModel)

UI state (локальный remember)

Overlay живут только в root Box/Scaffold.

Если компонент нельзя использовать в Preview — он разбит неправильно.

Если компонент нельзя переиспользовать в другом экране — он разбит неправильно.

Экспериментальные API изолируются в wrapper.

Большой composable всегда режется минимум на 3 части.