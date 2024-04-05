
# all permutations of a string
import math

def string_permutations(original: list[str], results: list[str], pos: int = 0):
    """
    TODO: describe
    """

    if pos == len(original) - 1:
        results.append(''.join(original))

    for i in range(pos, len(original)):
        tmp = original[pos]
        original[pos] = original[i]
        original[i] = tmp
     
        string_permutations(original, results, pos + 1)

        tmp = original[pos]
        original[pos] = original[i]
        original[i] = tmp

# all combinations of a string
def string_combinations(original: str):

    if len(original) == 0:
        return ['']

    results = []

    subcombs = string_combinations(original[1:])

    for subc in subcombs:
        results.append(original[0] + subc)
        results.append(subc)

    return results


orig = "abcd"
perms = []
string_permutations(list(orig), perms)
combs = string_combinations(list(orig))
print(f"ORIGINAL STRING: {orig}")
print(f"Has {len(perms)} permutations: {perms}")
print(f"Has {len(combs)} combinations: {combs}")

assert(len(perms) == math.factorial(len(orig)))
assert(len(combs) == math.pow(2, len(orig)))


