package com.telegrambot.funcompas.controller;

//@RestController
public class BotController {
  /*  private final PlaceMethods placeMethods;

    public BotController(PlaceMethods placeMethods) {
        this.placeMethods = placeMethods;
    }

  @GetMapping("/category/{id}")
  @ResponseStatus(HttpStatus.OK)
    //public void onUpdateReceivedController(@RequestBody Update update){
     //    telegramBot.onUpdateReceived(update);
    public List<Place> getByCategoryId(@PathVariable Integer id) {
       return placeMethods.getByCategory(id);//.getbyId(id).orElseThrow(() -> new NoteNotFoundException(id));
    }

    @GetMapping("place/{name}")
    public Optional<Place> getByPlaceName(@PathVariable String name){
        return placeMethods.getByName(name);//botMethods.getByPlaceId(id);
    }

   // @GetMapping()
  //  public List<Place>
    /*
              @GetMapping("/{id}")
              public Place get(@PathVariable Integer id) {
                  return todoListCRUD.getbyId(id).orElseThrow(() -> new NoteNotFoundException(id));
              }

              @ResponseStatus(HttpStatus.NO_CONTENT)
              @PutMapping("/{id}")
              public void update(@PathVariable Integer id, @RequestBody SaveNoteRequest request){
                  todoListCRUD.update(id, request);
              }

              @DeleteMapping("/{id}")
              public ToDoList delete(@PathVariable Integer id){
                  return todoListCRUD.deleteById(id).orElseThrow(()->new NoteNotFoundException(id));
              }

        @GetMapping
        public List<Place> getNotDone(){
            return todoListCRUD.findNotDone();
        }

        @GetMapping(value = "/all")
        public List<Place> print(){
            return todoListCRUD.printAll();
        }*/

    }
