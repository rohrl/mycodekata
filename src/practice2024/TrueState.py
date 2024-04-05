"""
CODE CHALLENGE: MARS ROVER!

GOAL:

Build an OO program that takes a list of commands for the Mars Rover and shows the result position and direction of the rover.

INITIAL REQUIREMENTS:

The input of the program is:

- The rover's starting point (x, y) and the direction it is facing ("N", "S", "E" or "W").
- A list of commands to move and turn the rover: "f" to move forward, "l": to turn left (90 degrees), "r" to turn right (90 degrees), and "b" to move backward.
After the string of commands, the rover can report its new position and direction.
"""


class MarsRover:
    """
    x is east-west, y is north-south
    """

    AXES = {
        'E': 1,
        'W': -1,
        'S': -1,
        'N': 1
    }

    # TODO: init using CLOCKWISE_DIR_ORDER.index
    ROTATION_LOOKUP = {
        'N': 0,
        'E': 1,
        'S': 2,
        'W': 3
    }
    CLOCKWISE_DIR_ORDER = ['N', 'E', 'S', 'W']

    # FIXME add enum

    def __validate_dir(self, dir: str):
        if dir not in 'NSEW':
            raise Exception("Direction value not supported")

    def __init__(self, x: int, y: int, direction: str):
        self.__validate_dir(direction)
        self.x = x
        self.y = y
        self.direction = direction

    def report_status(self):
        print(f"Position: x={self.x}, y={self.y}, direction={self.direction}")

    def move(self, instruction: str):
        # TODO: check boundaries
        # TODO __validate_instruction(instruction), lowercase

        if instruction == 'f':
            step = 1
        elif instruction == 'b':
            step = -1

        if self.direction in ['N', 'S']:
            # north-soutch axis
            self.y += step * MarsRover.AXES[self.direction]
        else:
            # east-west axis
            self.x += step * MarsRover.AXES[self.direction]

        ob = obstacles[obstacle_key(self.x, self.y)]
        if ob:
            # TODO
            pass

    def obstacle_key(self, x, y):
        return f"{x},{y}"

    def rotate(self, instruction):
        # TODO validate instruction
        if instruction == 'r':
            step = 1
        elif instruction == 'l':
            step = -1
            
        new_dir_idx = (MarsRover.ROTATION_LOOKUP[self.direction] + step) % len(MarsRover.CLOCKWISE_DIR_ORDER)
        self.direction = MarsRover.CLOCKWISE_DIR_ORDER[new_dir_idx]

    def command(self, instructions: str):
        for instruction in instructions:
            if instruction in ['f', 'b']:
                self.move(instruction)
            elif instruction in ['l', 'r']:
                self.rotate(instruction)
            else:
                raise Exception(f"Unsupported instruction {instruction}")


if __name__ == '__main__':
    rover = MarsRover(0, 0, 'N')
    rover.command('llffrbb')
    rover.report_status()
